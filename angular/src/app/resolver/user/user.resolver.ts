import { inject, Injectable } from "@angular/core";
import { Resolve } from "@angular/router";
import { Store } from "@ngrx/store";
import { Observable, filter, take, tap } from "rxjs";
import { User } from "../../models/user.model";
import { loadUsers } from "../../store/user/user.actions";
import { selectAllUsers } from "../../store/user/user.selectors";

@Injectable({ providedIn: 'root' })
export class UserResolver implements Resolve<User[]> {
  private store = inject(Store);

  resolve(): Observable<User[]> {
    this.store.dispatch(loadUsers()); // ✅ Dispatch action to load users

    return this.store.select(selectAllUsers).pipe(
      tap((users) => console.log('Users loaded in resolver:', users)), // ✅ Debugging
      filter((users) => users.length > 0), // ✅ Wait until users are loaded
      take(1) // ✅ Complete the observable after first valid data
    );
  }
}