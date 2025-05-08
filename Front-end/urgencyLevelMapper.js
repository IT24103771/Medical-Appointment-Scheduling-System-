// This script maps the urgency level from the dropdown to backend enum values

function getMappedUrgencyLevel() {
    const urgencyMap = {
        routine: "LOW",
        urgent: "MEDIUM",
        emergency: "HIGH"
    };

    const selected = document.getElementById('urgencyLevel').value;
    return urgencyMap[selected] || null;
}

// Example usage (optional):
document.getElementById('urgencyLevel').addEventListener('change', () => {
    const mapped = getMappedUrgencyLevel();
    console.log("Mapped urgency level:", mapped); // You can use this in your logic
});
