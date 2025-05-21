document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('appointmentForm');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const appointmentData = {
            firstName: document.getElementById('firstName').value,
            lastName: document.getElementById('lastName').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            dob: document.getElementById('dob')?.value || "", // Optional if not used
            appointmentDate: document.getElementById('appointmentDate').value,
            department: document.getElementById('department').value,
            doctor: document.getElementById('doctor').value,
            notes: document.getElementById('notes').value,
            urgencyLevel: document.getElementById('urgencyLevel').value
        };

        try {
            // Post to unsorted urgency-based controller
            const saveToUrgencyQueue = fetch('http://localhost:8080/api/appointments/save', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(appointmentData)
            });

            // Post to sorted-by-time controller
            const saveToSortedTime = fetch('http://localhost:8080/api/sorted-appointments/save', {
                method: 'POST',
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(appointmentData)
            });

            // Wait for both to complete
            const [urgencyResponse, timeResponse] = await Promise.all([
                saveToUrgencyQueue,
                saveToSortedTime
            ]);

            if (urgencyResponse.ok && timeResponse.ok) {
                alert("Appointment saved successfully");
                form.reset();
            } else {
                alert("One or more saves failed. Please check backend logs.");
            }

        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while saving the appointment.");
        }
    });
});
s