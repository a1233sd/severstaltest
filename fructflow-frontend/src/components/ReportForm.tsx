import React, { useState, FormEvent } from 'react';

interface ReportFormProps {
    onSubmit: (startDate: string, endDate: string) => void;
}

const ReportForm: React.FC<ReportFormProps> = ({ onSubmit }) => {
    const [startDate, setStartDate] = useState<string>('');
    const [endDate, setEndDate] = useState<string>('');

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        onSubmit(startDate, endDate);
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: '2rem' }}>
            <div className="form-group">
                <label>Дата начала:</label>
                <input
                    type="date"
                    value={startDate}
                    onChange={(e) => setStartDate(e.target.value)}
                    required
                />
            </div>
            <div className="form-group">
                <label>Дата окончания:</label>
                <input
                    type="date"
                    value={endDate}
                    onChange={(e) => setEndDate(e.target.value)}
                    required
                />
            </div>
            <button type="submit">Сформировать отчет</button>
        </form>
    );
};

export default ReportForm;
