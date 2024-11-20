export interface RegisterRequest {
    username: string;
    firstName: string;
    lastName: string;
    email: string;
    emailVerified: boolean; // Siempre es false
    enabled: boolean; // Siempre es true
    password: string;
    age: number;
    bio: string;
    webPage?: string | null; // Es opcional y nulo si es cliente
  }
  