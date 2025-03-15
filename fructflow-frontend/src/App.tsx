import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Deliveries from './pages/Deliveries';
import Reports from './pages/Reports';
import NotFound from './pages/NotFound';

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Dashboard />} />
                <Route path="/deliveries" element={<Deliveries />} />
                <Route path="/reports" element={<Reports />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
        </BrowserRouter>

    );
};

export default App;
