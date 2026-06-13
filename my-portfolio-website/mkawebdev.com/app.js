const express = require('express');
const path = require('path');

const projects = require('./data/projects');
const testingAreas = require('./data/testingAreas');
const site = require('./data/site');

const app = express();
const PORT = process.env.PORT || 3000;

// EJS setup
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Public static files
app.use(express.static(path.join(__dirname, 'public')));
app.use((req, res, next) => {
  res.locals.site = site;
  next();
});

app.get('/', (req, res) => {
  res.render('pages/home', {
    title: 'Home',
    currentPage: 'home'
  });
});

app.get('/projects', (req, res) => {
  res.render('pages/projects', {
    title: 'Projects',
    currentPage: 'projects',
    projects
  });
});

app.get('/projects/:slug', (req, res) => {
  const project = projects.find(project => project.slug === req.params.slug);

  if (!project) {
    return res.status(404).render('pages/404', {
      title: 'Project not found',
      currentPage: 'projects'
    });
  }

  res.render('pages/project-detail', {
    title: project.title,
    currentPage: 'projects',
    project
  });
});

app.get('/testing', (req, res) => {
  res.render('pages/testing', {
    title: 'Testing',
    currentPage: 'testing',
    testingAreas
  });
});

app.get('/accessibility', (req, res) => {
  res.render('pages/accessibility', {
    title: 'Accessibility',
    currentPage: 'accessibility'
  });
});

app.get('/about', (req, res) => {
  res.render('pages/about', {
    title: 'About',
    currentPage: 'about'
  });
});

app.get('/contact', (req, res) => {
  res.render('pages/contact', {
    title: 'Contact',
    currentPage: 'contact'
  });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});