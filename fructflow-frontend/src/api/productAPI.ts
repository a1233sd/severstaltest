import { BASE_URL } from './index';
import { Product } from '../types/Product';

export const getProducts = async (): Promise<Product[]> => {
    const response = await fetch(`${BASE_URL}/products`);
    if (!response.ok) {
        throw new Error('Error fetching products');
    }
    return response.json();
};

export const getProductById = async (id: number): Promise<Product> => {
    const response = await fetch(`${BASE_URL}/products/${id}`);
    if (!response.ok) {
        throw new Error(`Error fetching product with id ${id}`);
    }
    return response.json();
};

export const createProduct = async (product: Product): Promise<Product> => {
    const response = await fetch(`${BASE_URL}/products`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product),
    });
    if (!response.ok) {
        throw new Error('Error creating product');
    }
    return response.json();
};

export const updateProduct = async (id: number, product: Product): Promise<Product> => {
    const response = await fetch(`${BASE_URL}/products/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product),
    });
    if (!response.ok) {
        throw new Error(`Error updating product with id ${id}`);
    }
    return response.json();
};

export const deleteProduct = async (id: number): Promise<void> => {
    const response = await fetch(`${BASE_URL}/products/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error(`Error deleting product with id ${id}`);
    }
};
