import React from 'react';
import './Header.css'; // Assuming basic styles are here

function Header() {
  // Using '#' for in-page navigation anchors
  const navItems = [
    { name: 'Home', href: '#home' },
    { name: 'About', href: '#about' },
    { name: 'Experience', href: '#experience' },
    { name: 'Projects', href: '#projects' },
    { name: 'Contact', href: '#contact' },
    // Add Blog/List later if needed
  ];

  return (
    <header className="header">
      <div className="header-container"> {/* Optional: for centering/padding */}
        <div className="logo">
          {/* Replace with actual name or logo */}
          <h1>Bakiyalakshmi Palani</h1>
        </div>
        <nav className="main-nav">
          <ul>
            {navItems.map(item => (
              <li key={item.name}>
                <a href={item.href}>{item.name}</a>
              </li>
            ))}
          </ul>
        </nav>
      </div>
    </header>
  );
}

export default Header;
