import { inject, Injectable } from "@angular/core";
import { UserService } from "../../services/api/user.service";
import { loadUsers, loadUsersSuccess, loadUsersFailure } from "./user.actions";
import { catchError, exhaustMap, of, map, mergeMap, tap } from 'rxjs';
import { Actions, createEffect, ofType } from '@ngrx/effects';

@Injectable({
  providedIn: 'root' // ✅ Make sure it's provided in root
})
export class UserEffects {

  // constructor(private actions$: Actions, private userService: UserService) {}

  private actions$ = inject(Actions);
  private userService = inject(UserService);

  loadUsers$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadUsers),
      mergeMap(() =>
        this.userService.getAll().pipe(
          map((users) => loadUsersSuccess({ users })),
          catchError((error) => of(loadUsersFailure({ error: error.message })))
        )
      )
    )
  );

}