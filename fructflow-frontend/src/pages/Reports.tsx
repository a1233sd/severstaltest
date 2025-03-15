import React, { useState } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import ReportForm from '../components/ReportForm';
import ReportTable from '../components/ReportTable';
import { Report } from '../types/Report';
import * as reportAPI from '../api/reportAPI';

const Reports: React.FC = () => {
    const [reports, setReports] = useState<Report[]>([]);

    const handleGenerateReport = async (startDate: string, endDate: string) => {
        try {
            const data = await reportAPI.getReport(startDate, endDate);
            setReports(data);
        } catch (error) {
            console.error('Ошибка при формировании отчёта', error);
        }
    };

    return (
        <div>
            <Header />
            <main style={{ padding: '1rem' }}>
                <h2>Отчёты</h2>
                <ReportForm onSubmit={handleGenerateReport} />
                <ReportTable reports={reports} />
            </main>
            <Footer />
        </div>
    );
};

export default Reports;
