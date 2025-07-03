# 🛒 AppStore – Full Stack eCommerce Application with OTP Verification and Secure Checkout

AppStore is a full stack eCommerce web application built using **Spring Boot** for the backend and **HTML, CSS, and JavaScript** for the frontend. The application supports secure user registration, OTP verification, product browsing, cart management, and payment-integrated checkout.


## 🚀 Features

- ✅ **User Registration with OTP Verification**
- 🔐 Secure login after OTP confirmation
- 🔄 Forgot Password via email OTP and password reset
- 🛍️ View all available products
- 🛒 Add products to cart
- 🧾 Cart page with selected products
- 💳 Payment mode selection
- 📦 Order placement with customer details and delivery address
- ✅ Success message on successful order

## 🔄 User Flow

1. **User Registration**
   - User enters `username`, `password`, and `email`
   - OTP is sent to the registered email
   - Redirected to OTP verification page

2. **OTP Verification**
   - User enters `username` and the received OTP
   - On success: redirected to login page
   - On failure: shown message for invalid OTP or username

3. **Login**
   - Successful login redirects to **Product Dashboard**

4. **Forgot Password**
   - Click 'Forgot Password?' on login page
   - Enter registered email and send reset link
   - Enter `username`, OTP, and new password in reset form
   - Password is changed and redirected to login page

5. **Product Dashboard**
   - Click **View Products** to list all available products

6. **Cart Management**
   - From View Products page, click **Go to Cart** to see added products

7. **Checkout**
   - Select payment method
   - Enter customer details and delivery address
   - Click **Proceed to Checkout**
   - Order is placed and success message is shows

## 💻 Tech Stack

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Spring Boot, Java
- **Database:** MySQL / H2
- **Email/SMS:** OTP integration via JavaMailSender or Twilio

## 📂 Project Structure

AppStore/
├── frontend/
│ ├── register.html
│ ├── otp-verify.html
│ ├── login.html
│ ├── forgot-password.html
│ ├── reset-password.html
│ ├── product-dashboard.html
│ ├── view-products.html
│ ├── cart.html
│ └── checkout.html
├── backend/
│ ├── controller/
│ ├── model/
│ ├── repository/
│ ├── service/
│ └── config/

## 📧 Contact

Madhura Madhava Santhosh 
madhavasanthosh6@gmail.com

For any queries or improvements, feel free to open an issue or pull request!
