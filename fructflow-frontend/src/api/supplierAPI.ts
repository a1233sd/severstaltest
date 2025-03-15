import { BASE_URL } from './index';
import { Supplier } from '../types/Supplier';

export const getSuppliers = async (): Promise<Supplier[]> => {
    const response = await fetch(`${BASE_URL}/suppliers`);
    if (!response.ok) {
        throw new Error('Error fetching suppliers');
    }
    return response.json();
};

export const getSupplierById = async (id: number): Promise<Supplier> => {
    const response = await fetch(`${BASE_URL}/suppliers/${id}`);
    if (!response.ok) {
        throw new Error(`Error fetching supplier with id ${id}`);
    }
    return response.json();
};

export const createSupplier = async (supplier: Supplier): Promise<Supplier> => {
    const response = await fetch(`${BASE_URL}/suppliers`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(supplier),
    });
    if (!response.ok) {
        throw new Error('Error creating supplier');
    }
    return response.json();
};

export const updateSupplier = async (id: number, supplier: Supplier): Promise<Supplier> => {
    const response = await fetch(`${BASE_URL}/suppliers/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(supplier),
    });
    if (!response.ok) {
        throw new Error(`Error updating supplier with id ${id}`);
    }
    return response.json();
};

export const deleteSupplier = async (id: number): Promise<void> => {
    const response = await fetch(`${BASE_URL}/suppliers/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error(`Error deleting supplier with id ${id}`);
    }
};
