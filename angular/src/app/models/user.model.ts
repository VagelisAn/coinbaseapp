
export interface User {
  id?: number;
  firstname: string;
  lastname: string;
  email: string;
  mobile: number | string;
  phone: number | string;
  address?: string;
  city?: string;
  postcode?: number | string;
  contact?: string;
  keycloakId?: string;
}
