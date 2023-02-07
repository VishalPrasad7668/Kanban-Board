import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegistrationComponent } from './registration/registration.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatRadioModule } from '@angular/material/radio';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { NgToastModule } from 'ng-angular-popup'
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { LayoutModule } from '@angular/cdk/layout';
import { DragComponent } from './drag/drag.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { HeaderComponent } from './header/header.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { AlluserDetailsComponent } from './alluser-details/alluser-details.component';
import { UpdateUserDetailsComponent } from './update-user-details/update-user-details.component';
import { AssignTaskComponent } from './assign-task/assign-task.component';
import { DisplayTaskComponent } from './display-task/display-task.component';
import { UserDetailsDialogComponent } from './user-details-dialog/user-details-dialog.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    PageNotFoundComponent,
    

    DragComponent,
    HeaderComponent,
    AddTaskComponent,
    EditTaskComponent,
    HomePageComponent,
    UserDetailsComponent,
    AlluserDetailsComponent,
    UpdateUserDetailsComponent,
    AssignTaskComponent,
    DisplayTaskComponent,
    UserDetailsDialogComponent,
    ContactUsComponent,
    ForgotPasswordComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatRadioModule,
    MatExpansionModule,
    FormsModule,
    HttpClientModule,
    MatIconModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatGridListModule,
    MatMenuModule,
    LayoutModule,
    DragDropModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatDialogModule,
    NgToastModule,
    NgxPaginationModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
