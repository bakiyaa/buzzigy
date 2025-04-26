import React from 'react';
import './Contact.css';
// Optional: Import icons if available (e.g., from react-icons)
// import { FaGithub, FaLinkedin, FaEnvelope, FaPhone, FaMapMarkerAlt } from 'react-icons/fa';

function Contact() {
  const contactDetails = {
    email: "bakiyapalani1997@gmail.com",
    phone: "7449150327",
    location: "Chennai-600129, Tamil Nadu, India",
    github: "https://github.com/bakiyaa", // Assumed username based on portfolio link
    linkedin: "https://linkedin.com/in/yourprofile", // Placeholder - PLEASE UPDATE
    portfolio: "https://bakiyaa.github.io/Portfolio"
  };

  return (
    <section id="contact" className="contact-section content-section">
      <h2>Contact</h2>
      <div className="contact-details">
        <p>
          {/* Optional: <FaMapMarkerAlt /> */} {contactDetails.location}
        </p>
        <p>
          {/* Optional: <FaEnvelope /> */} Email: <a href={`mailto:${contactDetails.email}`}>{contactDetails.email}</a>
        </p>
        <p>
          {/* Optional: <FaPhone /> */} Mobile: <a href={`tel:${contactDetails.phone}`}>{contactDetails.phone}</a>
        </p>
        <p>
          {/* Optional: <FaGithub /> */} GitHub: <a href={contactDetails.github} target="_blank" rel="noopener noreferrer">{contactDetails.github}</a>
        </p>
        <p>
          {/* Optional: <FaLinkedin /> */} LinkedIn: <a href={contactDetails.linkedin} target="_blank" rel="noopener noreferrer">{contactDetails.linkedin}</a> (Update link)
        </p>
         <p>
           Portfolio: <a href={contactDetails.portfolio} target="_blank" rel="noopener noreferrer">{contactDetails.portfolio}</a>
        </p>
      </div>
      {/* Optional: Add a contact form here later */}
    </section>
  );
}

export default Contact;
