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
    alt: 'Placeholder image for testing material preview.',
    caption: 'Testing material placeholder'
  }
];

const githubBase = 'https://github.com/mkawebdev/portfolio/tree/main';

module.exports = [
  {
    slug: 'portfolio-website',
    category: 'qa',
    title: 'Portfolio Website',
    type: 'Software Testing / QA Practice Project',
    summary:
      'Testing and QA work applied to this portfolio website as a practical Software Testing Bootcamp artefact. The project focuses on test design, accessibility checks, responsive behaviour, UI review, defect notes, and iterative improvements applied to a real website.',
    gallery: placeholderGallery,
    technologies: ['Test Design', 'Accessibility Testing', 'Responsive Testing', 'UI Review', 'Defect Notes'],
    stack: [
      {
        label: 'Project type',
        value: 'Personal portfolio website testing and QA project'
      },
      {
        label: 'Application under test',
        value: 'mkawebdev.com portfolio website'
      },
      {
        label: 'Testing context',
        value: 'Software Testing Bootcamp practice artefacts applied to a real personal website'
      },
      {
        label: 'Testing focus',
        value: 'Test scenarios, responsive layout checks, accessibility review, UI behaviour, content structure, and defect documentation'
      },
      {
        label: 'Accessibility focus',
        value: 'Semantic structure, headings, keyboard focus, table markup, accordion behaviour, accessible labels, and WCAG-aware review'
      },
      {
        label: 'Supporting material',
        value: 'Testing notes, defect observations, screenshots, accessibility checks, and portfolio improvement records'
      },
      {
        label: 'Repository status',
        value: 'Testing artefacts will be stored with the project repository as the website is revised'
      }
    ],
    status: 'STB artefacts in progress',
    githubReadme: `${githubBase}/my-portfolio-website`
  },

  {
    slug: 'nuscan-android-app-testing-review',
    category: 'qa',
    title: 'NuScan Android App Testing Review',
    type: 'Software Testing / QA Practice Project',
    summary:
      'Planned testing and QA review of the existing NuScan Android app after graduation. This future review will focus on mobile testing artefacts, including test design, Android workflow checks, Room database behaviour, intent behaviour, permissions, import behaviour, and defect notes.',
    gallery: placeholderGallery,
    technologies: ['Mobile Testing', 'Android QA', 'Test Design', 'Room / SQLite', 'Defect Notes'],
    stack: [
      {
        label: 'Project type',
        value: 'Planned mobile app testing and QA review'
      },
      {
        label: 'Application under test',
        value: 'NuScan Android App'
      },
      {
        label: 'Testing context',
        value: 'Future testing-focused review of an existing university Android project'
      },
      {
        label: 'Testing focus',
        value: 'Android workflows, Room database behaviour, local data validation, search behaviour, map intents, email intents, permissions, and remote JSON import'
      },
      {
        label: 'Planned artefacts',
        value: 'Test scenarios, test cases, defect reports, screenshots, mobile workflow notes, and revised testing documentation'
      },
      {
        label: 'Repository status',
        value: 'Testing artefacts will be added later when the NuScan project is reviewed from a software testing perspective'
      }
    ],
    status: 'Planned after graduation',
    githubReadme: `${githubBase}/software-testing-projects/nuscan-android-app-testing-review`
  },

  {
    slug: 'whiskey-product-information-system',
    category: 'uni',
    title: 'Whiskey Product Information System',
    type: 'Software Design and Development Unit',
    summary:
      'JavaFX product information system developed for the Software Design and Development unit. The project includes database interaction, validation logic, product query behaviour, and existing JUnit 5 testing material.',
    gallery: placeholderGallery,
    technologies: ['Java', 'JavaFX', 'MySQL', 'JUnit 5'],
    stack: [
      {
        label: 'Project type',
        value: 'Individual university project'
      },
      {
        label: 'My contribution',
        value: 'Individual implementation, validation logic, database interaction, and testing documentation'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'Application framework',
        value: 'JavaFX'
      },
      {
        label: 'User interface',
        value: 'FXML and JavaFX views'
      },
      {
        label: 'Data storage',
        value: 'MySQL database accessed with JDBC'
      },
      {
        label: 'Build tool',
        value: 'Maven'
      },
      {
        label: 'Testing suite',
        value: 'JUnit 5'
      },
      {
        label: 'Testing focus',
        value: 'Validation logic, product data handling, login/password validation, and record navigation behaviour'
      },
      {
        label: 'Supporting material',
        value: 'Runnable application screenshots and JUnit 5 test result screenshots'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/whiskey-product-information-system`
  },

  {
    slug: 'wisdom-game',
    category: 'uni',
    title: 'Wisdom',
    type: 'Game Development Unit',
    summary:
      'Collaborative Unity/C# 2D game project developed by Team Mmm Mmm Jeans. The project included gameplay programming, puzzle and inventory interaction design, playtesting, feedback analysis, and iterative refinement. My contribution focused on gameplay interaction work, playtesting coordination, feedback analysis, and technical documentation.',
    gallery: placeholderGallery,
    technologies: ['Unity', 'C#', 'WebGL', 'Game Design', 'Playtesting'],
    stack: [
      {
        label: 'Project type',
        value: 'Five-member university team project'
      },
      {
        label: 'My contribution',
        value: 'Gameplay interaction work, puzzle and inventory interaction design contribution, playtesting coordination, feedback analysis, feedback-driven iteration, and technical documentation'
      },
      {
        label: 'Programming language',
        value: 'C#'
      },
      {
        label: 'Game engine',
        value: 'Unity'
      },
      {
        label: 'Build target',
        value: 'WebGL playable release'
      },
      {
        label: 'Team',
        value: 'Team Mmm Mmm Jeans'
      },
      {
        label: 'Testing focus',
        value: 'Playtesting, player feedback collection, usability issues, puzzle clarity, interaction cues, and feedback-driven iteration'
      },
      {
        label: 'Supporting material',
        value: 'Public playable itch.io release and portfolio-safe Markdown documentation'
      },
      {
        label: 'Repository status',
        value: 'Unity source project not published; public portfolio material provided'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/game-development-projects/wisdom`,
    externalLinks: [
      {
        label: 'Play on itch.io',
        url: 'https://mmm-mmm-jeans.itch.io/wisdom',
        icon: 'fa-solid fa-gamepad'
      }
    ]
  },

  {
    slug: 'audio-track-chat-app',
    category: 'uni',
    title: 'Audio Track Chat App',
    type: 'Enterprise Software Development Unit',
    summary:
      'Enterprise Software Development team project based on a Twitter/X and Spotify-style brief. The app used the iTunes Search API and supported authenticated users, friend chat, audio track search, track sharing, playlists/lists, ratings, and suggestions. My main contribution was developing the JUnit 5 and Mockito test suite, which supported the project testing documentation.',
    gallery: placeholderGallery,
    technologies: ['Spring Boot', 'Java', 'Thymeleaf', 'Spring Data JPA', 'JUnit 5', 'Mockito', 'iTunes Search API'],
    stack: [
      {
        label: 'Project type',
        value: 'Five-member university team project'
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
        label: 'Application framework',
        value: 'Spring Boot'
      },
      {
        label: 'User interface',
        value: 'Thymeleaf templates'
      },
      {
        label: 'Data storage',
        value: 'Spring Data JPA'
      },
      {
        label: 'External service',
        value: 'iTunes Search API'
      },
      {
        label: 'Build tool',
        value: 'Maven'
      },
      {
        label: 'Testing suite',
        value: 'JUnit 5 and Mockito'
      },
      {
        label: 'Testing focus',
        value: 'Unit testing, service-layer testing, and mocked dependencies'
      },
      {
        label: 'Supporting material',
        value: 'Automated test suite used to support QA documentation'
      },
      {
        label: 'Repository status',
        value: 'Original team repository private; public portfolio material provided'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/audio-track-chat-app`
  },

  {
    slug: 'nuscan-android-app',
    category: 'uni',
    title: 'NuScan Android App',
    type: 'Mobile Development Unit',
    summary:
      'Local-first Android product and nutrition catalogue app developed in Kotlin. The project included product records, tags, notes, structured nutrition rows, optional linked product images, Room database storage, remote JSON import, store records, map integration, and email sharing. The original unit focused on app design, implementation, and extension rather than a formal software testing process.',
    gallery: placeholderGallery,
    technologies: ['Android', 'Kotlin', 'Room', 'SQLite'],
    stack: [
      {
        label: 'Project type',
        value: 'Individual university mobile development project'
      },
      {
        label: 'My contribution',
        value: 'Individual Android app design, implementation, local data handling, product directory workflow, store features, map integration, email sharing, and remote JSON import'
      },
      {
        label: 'Programming language',
        value: 'Kotlin'
      },
      {
        label: 'Application framework',
        value: 'Android'
      },
      {
        label: 'User interface',
        value: 'XML layouts and Android screens/fragments'
      },
      {
        label: 'Data storage',
        value: 'Room and SQLite'
      },
      {
        label: 'External data source',
        value: 'Remote JSON import from GitHub-hosted data'
      },
      {
        label: 'Map feature',
        value: 'Google Maps screen for selected imported store records'
      },
      {
        label: 'Sharing feature',
        value: 'Android chooser intent used for email/share workflow'
      },
      {
        label: 'Build tool',
        value: 'Gradle'
      },
      {
        label: 'Original assessment focus',
        value: 'Assessment 2 focused on app design; Assessment 3 focused on implementation and extension'
      },
      {
        label: 'Testing process',
        value: 'The app was checked progressively during development, but a formal testing process or automated test suite was not required for the original unit submission'
      },
      {
        label: 'Future testing work',
        value: 'Testing artefacts may be added later through a separate Software Testing / QA review'
      },
      {
        label: 'Supporting material',
        value: 'Original design documentation, implementation documentation, extended low-fidelity screen-flow mock-up, icon sheet, Lucide icon license notice, screenshots, and future portfolio-safe supporting documents'
      }
    ],
    status: 'Original university project; testing review planned later',
    githubReadme: `${githubBase}/software-development-projects/nuscan-android-app`
  },

  {
    slug: 'online-accessibility-governance-platform',
    category: 'uni',
    title: 'Online Accessibility Governance Platform',
    type: 'Capstone Project',
    summary:
      'Team capstone project focused on accessibility governance, scan outputs, AI-supported remediation guidance, documentation, and final delivery. The platform used automated accessibility scanning to identify WCAG-related issues and connected scan output with AI-generated remediation guidance. My contribution was centred on project framing, requirements, governance thinking, documentation, coordination, and aligning artefacts with the platform purpose.',
    gallery: placeholderGallery,
    technologies: ['Spring Boot', 'Java', 'Thymeleaf', 'Node.js', 'Playwright', 'axe-core', 'SQLite'],
    stack: [
      {
        label: 'Project type',
        value: 'Five-member capstone team project'
      },
      {
        label: 'My contribution',
        value: 'Project framing, requirements, documentation, coordination, accessibility governance thinking, scanning module contribution, and presentation alignment'
      },
      {
        label: 'Programming language',
        value: 'Java and JavaScript'
      },
      {
        label: 'Application framework',
        value: 'Spring Boot'
      },
      {
        label: 'User interface',
        value: 'Thymeleaf templates'
      },
      {
        label: 'Data storage',
        value: 'SQLite'
      },
      {
        label: 'AI Integration',
        value: 'AI-supported remediation guidance generated from accessibility scan results'
      },
      {
        label: 'Build tool',
        value: 'Maven and npm'
      },
      {
        label: 'Testing suite',
        value: 'JUnit 5 and Mockito'
      },
      {
        label: 'Accessibility scanning tools',
        value: 'Playwright and axe-core'
      },
      {
        label: 'Testing focus',
        value: 'Unit testing, controller/service testing, accessibility scan outputs, WCAG-related findings, and AI-supported remediation guidance output'
      },
      {
        label: 'Supporting material',
        value: 'Project documentation, scan outputs, testing records, presentation artefacts, public repository links, and Docker image links'
      },
      {
        label: 'Role boundary',
        value: 'QA and testing responsibilities were led by the team QA / Testing role'
      }
    ],
    status: 'On standby',
    githubReadme: `${githubBase}/software-development-projects/online-accessibility-governance-platform`
  }
];