import React from 'react';

const Home: React.FC = () => {
  return (
    <div className="container" style={{ paddingTop: '60px', textAlign: 'center' }}>
      <h1 style={{ fontSize: '3rem', marginBottom: '20px' }}>Unleash Your Potential at FitZone</h1>
      <p style={{ fontSize: '1.2rem', color: 'var(--text-muted)', marginBottom: '40px', maxWidth: '600px', margin: '0 auto 40px' }}>
        Join the best gym in town with state-of-the-art equipment, expert trainers, and a community that pushes you forward.
      </p>
      <button className="btn-primary" style={{ fontSize: '1.2rem', padding: '15px 30px' }}>
        Start Your Journey
      </button>
    </div>
  );
};

export default Home;
