import React from 'react';
import './Projects.css';

function Projects() {
  const projectList = [
    {
      title: "GCP Carbon 3 Emissions Analysis",
      context: "Google GenAI Hackathon 2024 (Team Apollo18)",
      description: "Developed a comprehensive solution for carbon emissions analysis using the RAG framework. Leveraged BigQuery, Gemini API, and Vector AI Search for data processing pipelines, providing actionable insights. Utilized BigQuery for data handling and Vertex AI for predictive analytics.",
      tech: ["GCP", "RAG", "BigQuery", "Gemini API", "Vector AI Search", "Vertex AI"]
    },
    {
      title: "AWS - Payment Automation with AI & Cloud",
      context: "Personal/Professional Project",
      description: "Designed and implemented an SSI (Standard Settlement Instructions) automation solution using AWS Lambda, CloudFormation, Bedrock AI, and SQS. Improved accuracy and speed of financial transaction settlements and enhanced operational efficiency.",
      tech: ["AWS", "Lambda", "CloudFormation", "Bedrock AI", "SQS", "SSI Automation"]
    },
    {
      title: "Web Development - Restaurant App",
      context: "Johns Hopkins University Course ('HTML, CSS, and JavaScript for Web Developers', Oct 2022)",
      description: "Developed a functional restaurant website demonstrating expertise in core web technologies to build responsive, user-friendly interfaces.",
      tech: ["HTML", "CSS", "JavaScript"]
    },
    {
      title: "My Portfolio Website",
      context: "Personal Project",
      description: "Created and deployed this personal portfolio website using Angular, HTML, and CSS Grid, hosted on GitHub Pages. Showcases web development skills, technical projects, and achievements.",
      // Link added assuming it's the current project - adjust if needed
      link: "https://bakiyaa.github.io/Portfolio",
      tech: ["Angular", "HTML", "CSS Grid", "GitHub Pages", "React"] // Added React as we are building it now
    }
  ];

  return (
    <section id="projects" className="projects-section content-section">
      <h2>Projects</h2>
      <div className="projects-grid">
        {projectList.map((project, index) => (
          <div key={index} className="project-card">
            <h3>{project.title}</h3>
            <p className="project-context"><em>{project.context}</em></p>
            <p>{project.description}</p>
            {project.tech && (
              <div className="project-tech">
                <strong>Technologies:</strong>
                <ul>
                  {project.tech.map(t => <li key={t}>{t}</li>)}
                </ul>
              </div>
            )}
             {project.link && (
              <p className="project-link">
                <a href={project.link} target="_blank" rel="noopener noreferrer">View Project/Site</a>
              </p>
            )}
          </div>
        ))}
      </div>
    </section>
  );
}

export default Projects;
