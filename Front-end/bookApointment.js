document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('appointmentForm');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const appointmentData = {
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            dob: document.getElementById('dob').value,
            appointmentDate: document.getElementById('appointmentDate').value,
            department: document.getElementById('department').value,
            doctor: document.getElementById('doctor').value,
            notes: document.getElementById('notes').value,
            urgencyLevel: document.getElementById('urgencyLevel').value
        };


        console.log("appointmentData",appointmentData);

        try {
            const response = await fetch('http://localhost:8080/api/appointments/save', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(appointmentData) 
            });

            // if (response.ok) {
            //     const result = await response.json();
            //     alert("Appointment booked successfully!");
            //     form.reset();
            // } else {
            //     alert('Failed to book appointment. Please try again.');
            // }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred while booking the appointment.');
        }
    });
});
