import React from 'react';
import './Home.css';

// Import the section components
import AboutMe from '../AboutMe/AboutMe';
import Experience from '../Experience/Experience';
import Projects from '../Projects/Projects';
import Contact from '../Contact/Contact';
import Blog from '../Blog/Blog'; // Placeholder
import ImpossibleList from '../ImpossibleList/ImpossibleList'; // Placeholder

function Home() {
  return (
    <main id="home" className="home-container">
      {/* Render components within the grid area */}
      <div className="main-content-area">
        {/* Render sections - these will be laid out by Home.css grid */}
        <AboutMe />
        <Experience />
        <Projects />
        <Contact />
        <Blog /> {/* Placeholder section */}
        <ImpossibleList /> {/* Placeholder section */}
      </div>
    </main>
  );
}

export default Home;
