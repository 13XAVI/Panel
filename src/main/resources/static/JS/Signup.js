function validateForm() {
  // Get the form inputs
  var username = document.getElementById('username').value;
  var email = document.getElementById('email').value;
  var password = document.getElementById('password').value;

  // Validate username
  if (username.trim() === '') {
    alert('Please enter a username');
    return false;
  }

  // Validate email
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email.match(emailRegex)) {
    alert('Please enter a valid email address');
    return false;
  }

  // Validate password
  if (password.length < 6) {
    alert('Password must be at least 6 characters long');
    return false;
  }

  // Form is valid
  return true;
}
