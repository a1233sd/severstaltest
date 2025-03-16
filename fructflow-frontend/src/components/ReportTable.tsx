import React from 'react';
import { Report } from '../types/Report';

interface ReportTableProps {
    reports: Report[];
}

const ReportTable: React.FC<ReportTableProps> = ({ reports }) => {
    return (
        <div>
            <h2>Отчет</h2>
            {reports.length === 0 ? (
                <p>Нет данных для отчета.</p>
            ) : (
                <table border={1} cellPadding={5} style={{ width: '100%', borderCollapse: 'collapse', marginTop: '1rem' }}>
                    <thead style={{ backgroundColor: '#4a148c', color: '#fff' }}>
                    <tr>
                        <th>ID поставщика</th>
                        <th>Название поставщика</th>
                        <th>Общий вес</th>
                        <th>Общая стоимость</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reports.map((report, index) => (
                        <tr key={index}>
                            <td>{report.supplierId}</td>
                            <td>{report.supplierName}</td>
                            <td>{report.totalQuantity}</td>
                            <td>{report.totalCost}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default ReportTable;
