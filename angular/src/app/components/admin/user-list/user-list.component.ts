import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { InputTextModule } from 'primeng/inputtext';
import { UserService } from '../../../services/api/user.service';
import { User } from '../../../models/user.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [RouterModule, CommonModule, TableModule, ButtonModule, ToolbarModule, ProgressSpinnerModule, InputTextModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  private userService = inject(UserService);
  users = signal<User[]>([]);
  isLoading = signal<boolean>(true);


  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAll().subscribe({
      next: (data) => {
        console.log(data)
        this.users.set(data);
        this.isLoading.set(false);
      },
      error: (error) => {
        console.error('Error fetching owners:', error);
        this.isLoading.set(false);
      }
    });
  }


  openDeleteDialog(user: User) {
  //   if (owner.id) {
  //     this.deleteDialogService.openDeleteDialog(
  //       generateMessage(DELETE_MESSAGE,
  //         `${owner.firstname} ${owner.lastname}`
  //       ),
  //       DELETE_TITLE,
  //       deleteOwner({ id: Number(owner.id) }),
  //       generateMessage(
  //         DETAIL_SUCCESS_TOASTER,
  //         ` διαγραφή του ${owner.firstname} ${owner.lastname}`
  //       ),
  //       generateMessage(
  //         DETAIL_ERROR_TOASTER,
  //         ` διαγραφή του ${owner.firstname} ${owner.lastname}`
  //       ),
  //       DELETE_REJECT_MESSAGE,
  //       DELETE_CANCEL_MESSAGE,
  //       this.success$,
  //       this.error$
  //     );
  //   }
  }

  addOwnerDialog() {
  //   this.visibleOwnerDialog = this.visibleOwnerDialog === false ? true : false;
  }
  
  visibleFormOwnerDialog(event: { visible: boolean }) {
  //   this.visibleOwnerDialog = event.visible;
  }

  async exportToPDF() {
  //   const pdfBlob = await this.pdfService.generateOwnerPdf(this.owners);
  //   const url = URL.createObjectURL(pdfBlob);
  //   const link = document.createElement('a');
  //   link.href = url;
  //   link.download = 'owners-list.pdf';
  //   link.click();
  //   URL.revokeObjectURL(url); // Clean
  }
}