export function authHeader () {
  // return authorization header with jwt token
  const user = localStorage.getItem('user')

  if (user) {
    return { Authorization: 'Bearer ' + user }
  } else {
    return {}
  }
}
