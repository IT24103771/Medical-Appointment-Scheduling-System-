    document.getElementById("registraionForm").addEventListener("submit", async function(event) {
        event.preventDefault(); 

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const location = document.getElementById("location").value;
        const fullname  = document.getElementById("fullname").value;
        const phonenumber  = document.getElementById("phonenumber").value;
        const age  = document.getElementById("age").value;
        const emailAdd  = document.getElementById("emailAdd").value;
        
        
        console.log("emailAdd",emailAdd)

        const formData = {
            userName: username,
            password: password,
            location: location,
            fullName:fullname,
            phoneNo:phonenumber,
            age:age,
            emailAdd:emailAdd
        };

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            // if (response.ok) {
            //     const result = await response.json();
            //     alert("User registered successfully!");
            // } else {
            //     alert("Registration failed!");
            // }
        } catch (error) {
            // console.error("Error:", error);
            // alert("An error occurred!");
        }
    });