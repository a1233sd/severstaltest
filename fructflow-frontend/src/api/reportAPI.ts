import { BASE_URL } from './index';
import { Report } from '../types/Report';

export const getReport = async (startDate: string, endDate: string): Promise<Report[]> => {
    const response = await fetch(`${BASE_URL}/reports?startDate=${encodeURIComponent(startDate)}&endDate=${encodeURIComponent(endDate)}`);
    if (!response.ok) {
        throw new Error('Error fetching report');
    }
    return response.json();
};
