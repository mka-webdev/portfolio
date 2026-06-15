import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 10,         // 10 concurrent users
  duration: '30s', // run for 30 seconds
};

const BASE = 'http://127.0.0.1:8080';
const USERS = [
  { username: 'user1',  password: 'password' },
  { username: 'user2',  password: 'password' },
  { username: 'user3',  password: 'password' },
  { username: 'user4',  password: 'password' },
  { username: 'user5',  password: 'password' },
  { username: 'user6',  password: 'password' },
  { username: 'user7',  password: 'password' },
  { username: 'user8',  password: 'password' },
  { username: 'user9',  password: 'password' },
  { username: 'user10', password: 'password' },
];

export default function () {
  const user = USERS[(__VU - 1) % USERS.length];

  // 1) login (CSRF disabled, form login allowed)
  const loginRes = http.post(`${BASE}/login`, {
    username: user.username,
    password: user.password,
  });

  check(loginRes, {
    'login ok': r => r.status === 200 || r.status === 302,
  });

  // carry session cookies forward
  const jar = http.cookieJar();
  for (const name in loginRes.cookies) {
    jar.set(BASE, name, loginRes.cookies[name][0].value);
  }

  // 2) /tests reachable (public per SecurityConfig)
  const testsRes = http.get(`${BASE}/tests`);
  check(testsRes, { '/tests reachable': r => r.status === 200 });

  sleep(1);
}
