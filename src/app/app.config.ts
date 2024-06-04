import { provideHttpClient, withFetch, withInterceptors } from "@angular/common/http";
import { ApplicationConfig } from "@angular/core";
import { authInterceptor } from "./interceptors/auth.interceptor";

export const appConfig: ApplicationConfig = {
    providers: [provideHttpClient(withFetch(), withInterceptors([authInterceptor]))]
}