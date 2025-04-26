import React from 'react';
import './AboutMe.css';

function AboutMe() {
  // Extracted Skills - categorized for better display
  const skills = {
    programming: ["Java (Primary)", "Python3 (Secondary)", "C++", "Data Structures (Algorithm Design, Problem Solving)"],
    web: ["Angular14", "HTML5", "CSS Grid", "JavaScript"],
    databases: ["Oracle", "MongoDB (storage and Aggregation)"],
    backend: ["Spring MVC", "Spring Boot", "Hibernate", "JPA", "Camel", "AOP", "SOAP", "Microservices", "REST"],
    devops: ["Docker", "Git", "Jenkins", "Unix/Linux (Scripts)"],
    messaging: ["MQ", "Kafka", "Distributed Systems", "Drool Engine"],
    cloud: ["Google Cloud Platform (GCP)", "Amazon Web Service (AWS)"],
    testing: ["Selenium (WebDriver)", "Junit-Mockito", "Grafana"],
    intermediate: ["Figma", "Thymeleaf", "Free Marker", "Eureka", "JMeter", "Cucumber", "Terraform"],
    languages: ["English (Fluent)", "Tamil", "French (Basic)"]
  };

  const achievements = [
    "Earned 22,150+ points on Google Cloud Skills Boost.",
    "Certified Aspiring Architect-L0 (19 July 2024).",
    "Completed 15+ courses within three months while successfully finishing a full marathon.",
    "Finalist in TESTIMONY-2K17 Quality Assurance Champion (Top 15 in India at TCS).",
    "Achieved 44th rank in Anna University (Batch 2018 - UG)."
  ];

  const professionalDevelopment = [
      "Part of the Google Developer Program – Google for Developers.",
      "Actively participated in sessions on Women in AI and the Global Architect Community."
  ];


  return (
    <section id="about" className="about-me-section content-section">
      <h2>About Me</h2>

      <div className="summary">
        <h3>Professional Summary</h3>
        <p>
          AWS Certified Developer Associate and experienced Full-Stack Developer with over 6.5 years of experience in enterprise
          integrations, API development, and cloud computing. I bring a deep understanding and expertise in Java, Angular, Spring
          Boot, and GCP, with hands-on experience in REST/SOAP APIs, CI/CD pipelines, and middleware solutions. Skilled in
          Salesforce integrations, cloud security, and performance optimization.
        </p>
      </div>

      <div className="skills">
        <h3>Skills</h3>
        {Object.entries(skills).map(([category, skillList]) => (
          <div key={category} className="skill-category">
            <h4>{category.charAt(0).toUpperCase() + category.slice(1)}</h4>
            <ul>
              {skillList.map(skill => <li key={skill}>{skill}</li>)}
            </ul>
          </div>
        ))}
      </div>

       <div className="achievements">
          <h3>Achievements</h3>
          <ul>
              {achievements.map((achievement, index) => <li key={index}>{achievement}</li>)}
          </ul>
       </div>

       <div className="professional-development">
            <h3>Professional Development & Interests</h3>
            <ul>
                {professionalDevelopment.map((item, index) => <li key={index}>{item}</li>)}
            </ul>
       </div>

    </section>
  );
}

export default AboutMe;
