import { Currency } from "./currency.model";

export interface News {
    title: string;
    published_at: string;
    currencies: Currency[] | null;
    url: string;
    created_at: string;
}