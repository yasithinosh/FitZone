import React from 'react';

const Footer: React.FC = () => {
  return (
    <footer style={{ 
      backgroundColor: 'var(--bg-card)', 
      padding: '40px 0 20px', 
      borderTop: '1px solid #333',
      marginTop: '60px'
    }}>
      <div className="container" style={{ textAlign: 'center', color: 'var(--text-muted)' }}>
        <div style={{ marginBottom: '20px', fontWeight: 'bold', fontSize: '1.2rem', color: 'var(--text-main)' }}>
          FitZone
        </div>
        <p>&copy; {new Date().getFullYear()} FitZone. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
