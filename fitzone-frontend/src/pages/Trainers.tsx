import React, { useEffect, useState } from 'react';
import api from '../utils/api';

interface Trainer {
  id: number;
  name: string;
  speciality: string;
  bio: string;
  rating: number;
}

const Trainers: React.FC = () => {
  const [trainers, setTrainers] = useState<Trainer[]>([]);

  useEffect(() => {
    const fetchTrainers = async () => {
      try {
        const res = await api.get('/trainers');
        if (res.data.success) {
          setTrainers(res.data.data);
        }
      } catch (error) {
        console.error('Failed to fetch trainers', error);
      }
    };
    fetchTrainers();
  }, []);

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px' }}>Meet Our Trainers</h2>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '20px' }}>
        {trainers.map(t => (
          <div key={t.id} className="card">
            <h3>{t.name}</h3>
            <p style={{ color: 'var(--primary-color)', fontWeight: 'bold' }}>{t.speciality}</p>
            <p style={{ marginTop: '10px' }}>{t.bio}</p>
            <p style={{ marginTop: '10px', color: 'var(--text-muted)' }}>Rating: {t.rating} / 5.0</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Trainers;
