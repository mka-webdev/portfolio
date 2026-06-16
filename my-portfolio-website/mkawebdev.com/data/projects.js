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
    slug: 'whiskey-product-information-system',
    category: 'uni',
    title: 'Whiskey Product Information System',
    type: 'Software Design and Development Unit',
    summary: 'JavaFX product information system developed for the Software Design and Development unit. The project includes database interaction, validation logic, product query behaviour, and existing JUnit 5 testing evidence.',
    gallery: placeholderGallery,
    technologies: ['Java', 'JavaFX', 'MySQL', 'JUnit 5'],
    stack: [
      {
        label: 'Project type',
        value: 'Individual university project'
      },
      {
        label: 'My contribution',
        value: 'Individual implementation, validation logic, database interaction, and testing evidence documentation'
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
        label: 'Evidence',
        value: 'Runnable application screenshots and JUnit 5 test result screenshots'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/whiskey-product-information-system`
  },
  {
    slug: 'audio-track-chat-app',
    category: 'uni',
    title: 'Audio Track Chat App',
    type: 'Enterprise Software Development Unit',
    summary: 'Enterprise Software Development team project based on a Twitter/X and Spotify-style brief. The app used the iTunes Search API and supported authenticated users, friend chat, audio track search, track sharing, playlists/lists, ratings, and suggestions. My main contribution was developing the JUnit 5 and Mockito test suite, which supported the testing evidence used in project documentation.',
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
        label: 'Evidence',
        value: 'Automated test suite used to support QA documentation'
      },
      {
        label: 'Repository status',
        value: 'Original team repository private; public portfolio evidence provided'
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
    summary: 'Local-first Android product and nutrition catalogue app with Room database, tags, stores, map integration, email sharing, and remote JSON import. This project can support mobile testing scenarios around permissions, intents, data storage, and import behaviour.',
    gallery: placeholderGallery,
    technologies: ['Android', 'Java', 'Room', 'SQLite'],
    stack: [
      {
        label: 'Project type',
        value: 'Individual university project'
      },
      {
        label: 'My contribution',
        value: 'Individual Android app implementation, local data handling, store features, map integration, email sharing, and remote JSON import'
      },
      {
        label: 'Programming language',
        value: 'Java'
      },
      {
        label: 'Application framework',
        value: 'Android'
      },
      {
        label: 'User interface',
        value: 'XML layouts and Android fragments'
      },
      {
        label: 'Data storage',
        value: 'Room and SQLite'
      },
      {
        label: 'External service',
        value: 'Remote JSON import from GitHub-hosted data'
      },
      {
        label: 'Build tool',
        value: 'Gradle'
      },
      {
        label: 'Testing suite',
        value: 'To be added'
      },
      {
        label: 'Testing focus',
        value: 'Permissions, intents, data storage, search behaviour, map behaviour, and import behaviour'
      },
      {
        label: 'Evidence',
        value: 'Application screenshots and mobile feature documentation'
      }
    ],
    status: 'Documentation draft',
    githubReadme: `${githubBase}/software-development-projects/nuscan-android-app`
  },
  {
    slug: 'online-accessibility-governance-platform',
    category: 'uni',
    title: 'Online Accessibility Governance Platform',
    type: 'Capstone Project',
    summary: 'Team capstone project focused on accessibility governance, scan evidence, AI-supported remediation guidance, documentation, and final delivery. The platform used automated accessibility scanning to identify WCAG-related issues and connected scan output with AI-generated remediation guidance. My contribution was centred on project framing, requirements, governance thinking, documentation, coordination, and aligning artefacts with the platform purpose.', gallery: placeholderGallery,
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
        value: 'Unit testing, controller/service testing, accessibility scan evidence, WCAG-related findings, and AI-supported remediation guidance output'
      },
      {
        label: 'Evidence',
        value: 'Project documentation, scan evidence, testing evidence, presentation artefacts, public repository links, and Docker image links'
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