export function authHeader () {
  // return authorization header with jwt token
  const user = localStorage.getItem('user')

  if (user) {
    return { Authorization: 'Bearer ' + user }
  } else {
    return {}
  }
}

export function isAuthenticated(): boolean {
  return (localStorage.getItem('user') != null)
}

export function isAdmin(): boolean {
  return true;
}