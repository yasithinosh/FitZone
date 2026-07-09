import React, { useEffect, useState } from 'react';
import api from '../utils/api';
import { useAuth } from '../context/AuthContext';

interface GymClass {
  id: number;
  name: string;
  description: string;
  category: string;
  intensity: string;
  trainerName: string;
  dayOfWeek: string;
  startTime: string;
  durationMin: number;
  capacity: number;
  currentCount: number;
}

const Classes: React.FC = () => {
  const [classes, setClasses] = useState<GymClass[]>([]);
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    const fetchClasses = async () => {
      try {
        const res = await api.get('/classes');
        if (res.data.success) {
          setClasses(res.data.data);
        }
      } catch (error) {
        console.error('Failed to fetch classes', error);
      }
    };
    fetchClasses();
  }, []);

  const handleBook = async (classId: number) => {
    try {
      // Typically need to select a date, stubbed for today
      const today = new Date().toISOString().split('T')[0];
      const res = await api.post('/bookings', { classId, bookingDate: today });
      if (res.data.success) {
        alert('Booked successfully!');
        // Ideally update local count
        setClasses(classes.map(c => c.id === classId ? { ...c, currentCount: c.currentCount + 1 } : c));
      }
    } catch (err) {
      alert('Booking failed. It may be full.');
    }
  };

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px' }}>Our Classes</h2>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '20px' }}>
        {classes.map(c => (
          <div key={c.id} className="card">
            <h3>{c.name}</h3>
            <p style={{ color: 'var(--text-muted)' }}>{c.description}</p>
            <div style={{ marginTop: '15px', marginBottom: '15px' }}>
              <strong>Category:</strong> {c.category} <br/>
              <strong>Intensity:</strong> {c.intensity} <br/>
              <strong>Trainer:</strong> {c.trainerName} <br/>
              <strong>When:</strong> {c.dayOfWeek} at {c.startTime} ({c.durationMin} min) <br/>
              <strong>Spots:</strong> {c.capacity - c.currentCount} available
            </div>
            {isAuthenticated && (
              <button 
                className="btn-primary" 
                onClick={() => handleBook(c.id)}
                disabled={c.currentCount >= c.capacity}
                style={{ width: '100%', opacity: c.currentCount >= c.capacity ? 0.5 : 1 }}
              >
                {c.currentCount >= c.capacity ? 'Full' : 'Book Now'}
              </button>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Classes;
