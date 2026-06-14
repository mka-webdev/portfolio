const placeholderGallery = [
  {
    src: '/img/placeholders/project-placeholder-1.svg',
    alt: 'Placeholder image for project screenshot area.',
    caption: 'Screenshot placeholder'
  },
  {
    src: '/img/placeholders/project-placeholder-2.svg',
    alt: 'Placeholder image for project documentation preview.',
    caption: 'Documentation placeholder'
  },
  {
    src: '/img/placeholders/project-placeholder-3.svg',
    alt: 'Placeholder image for testing evidence preview.',
    caption: 'Testing evidence placeholder'
  }
];

const githubBase = 'https://github.com/mkawebdev/portfolio/tree/main';

module.exports = [
  {
    slug: 'student-grade-system',
    title: 'Student Grade System',
    type: 'Software Design and Development Unit',
    summary: 'Java-based student grade management system developed as part of the Software Design and Development unit. This project can support testing documentation around validation, application logic, and expected grade calculation behaviour.',
    gallery: placeholderGallery,
    technologies: ['Java', 'JavaFX'],
    stack: [
      {
        label: 'Team',
        value: 'Individual project'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'User interface',
        value: 'JavaFX'
      },
      {
        label: 'Testing / evidence',
        value: 'Manual test scenarios, validation checks'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/student-grade-system`
  },
  {
    slug: 'whiskey-product-information-system',
    title: 'Whiskey Product Information System',
    type: 'Software Design and Development Unit',
    summary: 'Product information system developed for the Software Design and Development unit. This project can support testing documentation around product data, UI flow, search or filtering behaviour, and data handling.',
    gallery: placeholderGallery,
    technologies: ['Java', 'JavaFX'],
    stack: [
      {
        label: 'Team',
        value: 'Individual project'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'User interface',
        value: 'JavaFX'
      },
      {
        label: 'Testing / evidence',
        value: 'Manual test scenarios, UI flow checks, data handling checks'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/whiskey-product-information-system`
  },
  {
    slug: 'social-media-playlist-app',
    title: 'Social Media Playlist App',
    type: 'Enterprise Software Development Unit',
    summary: 'Enterprise Software Development team project using the iTunes Search API. The app supported track search, authenticated users, playlist or list creation, playlist sharing, and an internal chat feature. My main contribution was developing the JUnit 5 and Mockito test suite, which supported the testing evidence used in project documentation.',
    gallery: placeholderGallery,
    technologies: ['Spring Boot', 'Java', 'Thymeleaf', 'Spring Data JPA', 'JUnit 5', 'Mockito', 'iTunes Search API'],
    stack: [
    {
      label: 'Team',
      value: 'Five-member team: project Manager (myself), quality assurance Manager, technical Manager, and two developers'
    },
    {
      label: 'My contribution',
      value: 'Project management and development of the JUnit 5 and Mockito test suite'
    },
    {
      label: 'Programming language',
      value: 'Java'
    },
    {
      label: 'Framework',
      value: 'Spring Boot'
    },
    {
      label: 'Templating engine',
      value: 'Thymeleaf'
    },
    {
      label: 'Persistence',
      value: 'Spring Data JPA'
    },
    {
      label: 'External API',
      value: 'iTunes Search API'
    },
    {
      label: 'Testing suite / methodology',
      value: 'JUnit 5, Mockito, unit testing, service-layer testing, mocked dependencies'
    },
    {
      label: 'Testing evidence',
      value: 'Automated test suite used to support QA documentation'
    },
    {
      label: 'Repository',
      value: 'Original team repository private; public portfolio evidence provided'
    }
  ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/social-media-playlist-app`
  },
  {
    slug: 'nuscan-android-app',
    title: 'NuScan Android App',
    type: 'Mobile Development Unit',
    summary: 'Local-first Android product and nutrition catalogue app with Room database, tags, stores, map integration, email sharing, and remote JSON import. This project can support mobile testing scenarios around permissions, intents, data storage, and import behaviour.',
    gallery: placeholderGallery,
    technologies: ['Android', 'Java', 'Room', 'SQLite'],
    stack: [
      {
        label: 'Team',
        value: 'Individual project'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'Platform',
        value: 'Android'
      },
      {
        label: 'Database',
        value: 'Room, SQLite'
      },
      {
        label: 'User interface',
        value: 'XML layouts'
      },
      {
        label: 'Testing / evidence',
        value: 'Mobile test scenarios, permissions checks, intent checks, data storage checks'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/nuscan-android-app`
  },
  {
    slug: 'online-accessibility-governance-platform',
    title: 'Online Accessibility Governance Platform',
    type: 'Capstone Project',
    summary: 'Team capstone project focused on accessibility governance, scan evidence, remediation guidance, documentation, and final delivery. My contribution was centred on project framing, requirements, governance thinking, documentation, coordination, and aligning artefacts with the platform purpose.',
    gallery: placeholderGallery,
    technologies: ['Spring Boot', 'Java', 'Playwright', 'axe-core', 'SQLite', 'Thymeleaf'],
    stack: [
      {
        label: 'Team',
        value: 'Team capstone project'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'Framework',
        value: 'Spring Boot'
      },
      {
        label: 'Templating engine',
        value: 'Thymeleaf'
      },
      {
        label: 'Accessibility scanning',
        value: 'Playwright, axe-core'
      },
      {
        label: 'Database',
        value: 'SQLite'
      },
      {
        label: 'Testing / evidence',
        value: 'Accessibility scan evidence, requirements documentation, governance documentation'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/online-accessibility-governance-platform`
  },
  {
    slug: 'software-testing-bootcamp-artefacts',
    title: 'Software Testing Bootcamp Artefacts',
    type: 'Roshdy\'s Software Testing Bootcamp',
    summary: 'Structured testing artefacts covering test scenarios, test cases, defect reports, Postman, JMeter, Selenium, Katalon, SQL, screenshots, and accessibility defect examples.',
    gallery: placeholderGallery,
    technologies: ['Postman', 'JMeter', 'Selenium', 'Katalon', 'SQL'],
    stack: [
      {
        label: 'Team',
        value: 'Individual training portfolio'
      },
      {
        label: 'Testing approach',
        value: 'Manual testing, test scenarios, test cases, defect reporting'
      },
      {
        label: 'API testing',
        value: 'Postman'
      },
      {
        label: 'Performance testing',
        value: 'JMeter'
      },
      {
        label: 'UI automation',
        value: 'Selenium, Katalon'
      },
      {
        label: 'Database checks',
        value: 'SQL'
      },
      {
        label: 'Accessibility testing',
        value: 'Accessibility defect examples, WCAG-based observations'
      }
    ],
    status: 'In progress',
    githubReadme: `${githubBase}/testing-portfolio`
  }
];