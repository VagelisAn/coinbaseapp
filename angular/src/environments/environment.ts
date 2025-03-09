const authorityUrl: string = 'http://localhost:8080'; 
const realm: string = 'VetApp';

export const environment = {
    production: false,
    baseURL: 'http://localhost:8081',
    keycloak: {
        enable: true, 
        authorityUrl: authorityUrl, 
        redirectUri: 'http://localhost:4200', 
        postLogoutRedirectUri: 'http://localhost:4200/logout', 
        realm: realm, 
        clientId: 'angular-client',
        registryUrl: `${authorityUrl}/auth/realms/${realm}/users`,
        redirectUriLogInKeycloak: 'http://localhost:8080/realms/VetApp/protocol/openid-connect/auth?client_id=angular-client&redirect_uri=http%3A%2F%2Flocalhost%3A4200&state=887948a3-3379-4c5c-b864-5b404c036ca3&response_mode=fragment&response_type=code&scope=openid&nonce=a2dabba4-1d2f-4b47-bb1d-907ec8e31673&code_challenge=j_D0CBcsEwH70FcvuMoZDjkNrItT3PyZUwvatqTa3D8&code_challenge_method=S256',
        clientSecret: 'xeY1zRSGEiTDnYxo3o4Mn0hqiNn1V0ph',
        grantType: 'password',
        loginUrl: `${authorityUrl}/realms/${realm}/protocol/openid-connect/token`
      }
 };