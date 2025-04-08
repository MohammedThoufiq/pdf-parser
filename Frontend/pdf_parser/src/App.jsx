import React, { useState } from 'react';
import axios from 'axios';
import { saveAs } from 'file-saver';

function App() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [jsonData, setJsonData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
    setJsonData(null);
    setError('');
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      setError('Please select a PDF file.');
      return;
    }

    setLoading(true);
    setError('');

    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const response = await axios.post('http://localhost:8080/parse', formData);
      setJsonData(response.data);
    } catch (err) {
      setError('Failed to process PDF. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleExport = () => {
    const blob = new Blob([JSON.stringify(jsonData, null, 2)], { type: 'application/json' });
    saveAs(blob, 'parsed_output.json');
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-6">
      <h1 className="text-3xl font-bold mb-4">📄 PDF Parser</h1>

      <input
        type="file"
        accept="application/pdf"
        onChange={handleFileChange}
        className="mb-4"
      />

      <button
        onClick={handleUpload}
        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        disabled={loading}
      >
        {loading ? 'Processing...' : 'Upload & Parse'}
      </button>

      {error && <p className="text-red-600 mt-4">{error}</p>}

      {jsonData && (
        <div className="mt-6 w-full max-w-3xl">
          <h2 className="text-xl font-semibold mb-2">Parsed JSON:</h2>
          <pre className="bg-white p-4 rounded shadow max-h-96 overflow-auto text-sm">{JSON.stringify(jsonData, null, 2)}</pre>
          <button
            onClick={handleExport}
            className="mt-4 bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
          >
            Export JSON
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
