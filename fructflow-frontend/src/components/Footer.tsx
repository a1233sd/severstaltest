import React from 'react';

const Footer: React.FC = () => {
    return (
        <footer style={{ padding: '1rem', backgroundColor: '#4a148c', color: '#fff', marginTop: '2rem', textAlign: 'center' }}>
            <p>&copy; {new Date().getFullYear()}.</p>
        </footer>
    );
};

export default Footer;
