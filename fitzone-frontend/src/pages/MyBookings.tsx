import React, { useEffect, useState } from 'react';
import api from '../utils/api';

const MyBookings: React.FC = () => {
  const [bookings, setBookings] = useState<any[]>([]);

  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const res = await api.get('/bookings/my');
        if (res.data.success) {
          setBookings(res.data.data);
        }
      } catch (err) {
        console.error('Failed to fetch bookings', err);
      }
    };
    fetchBookings();
  }, []);

  const handleCancel = async (id: number) => {
    try {
      const res = await api.put(`/bookings/${id}/cancel`);
      if (res.data.success) {
        setBookings(bookings.map(b => b.id === id ? { ...b, status: 'CANCELLED' } : b));
      }
    } catch (err) {
      alert('Failed to cancel booking');
    }
  };

  return (
    <div className="container" style={{ paddingTop: '40px' }}>
      <h2 style={{ marginBottom: '30px' }}>My Bookings</h2>
      <div className="card">
        {bookings.length > 0 ? (
          <ul style={{ listStyle: 'none', padding: 0 }}>
            {bookings.map((b: any) => (
              <li key={b.id} style={{ display: 'flex', justifyContent: 'space-between', padding: '15px 0', borderBottom: '1px solid #333' }}>
                <div>
                  <strong>{b.className}</strong> with {b.trainerName} <br />
                  <span style={{ color: 'var(--text-muted)' }}>{b.bookingDate} at {b.startTime} ({b.durationMin} min)</span>
                  <div style={{ marginTop: '5px', color: b.status === 'CONFIRMED' ? '#4caf50' : '#f44336' }}>
                    {b.status}
                  </div>
                </div>
                {b.status === 'CONFIRMED' && (
                  <button onClick={() => handleCancel(b.id)} className="btn-primary" style={{ backgroundColor: '#f44336', alignSelf: 'center' }}>
                    Cancel
                  </button>
                )}
              </li>
            ))}
          </ul>
        ) : (
          <p style={{ color: 'var(--text-muted)' }}>No bookings found.</p>
        )}
      </div>
    </div>
  );
};

export default MyBookings;
