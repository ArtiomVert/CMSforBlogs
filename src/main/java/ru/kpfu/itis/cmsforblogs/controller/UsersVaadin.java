package ru.kpfu.itis.cmsforblogs.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.cmsforblogs.entity.User;
import ru.kpfu.itis.cmsforblogs.repository.UserRepository;
import ru.kpfu.itis.cmsforblogs.dictionary.UserRole;


@Route("admin/users")
public class UsersVaadin extends VerticalLayout {

    private final UserRepository userRepository;

    private Grid<User> userGrid = new Grid<>(User.class);

    private RadioButtonGroup<UserRole> roleRadio = new RadioButtonGroup<>();

    private User currentUser;

    public UsersVaadin(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;

        userGrid.setColumns("id", "username", "role");
        userGrid.setItems(userRepository.findAll());

        userGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                editUser(event.getValue());
            } else {
                clearForm();
            }
        });

        roleRadio.setLabel("Роль");
        roleRadio.setItems(UserRole.values());

        Button saveBtn = new Button("Сохранить", e -> saveUser());

        add(userGrid, roleRadio, saveBtn);
    }

    private void editUser(User user) {
        currentUser = user;
        roleRadio.setValue(user.getRole());
    }

    private void saveUser() {
        if (currentUser != null && roleRadio.getValue() != null) {
            currentUser.setRole(roleRadio.getValue());
            userRepository.save(currentUser);
            userGrid.setItems(userRepository.findAll());
            clearForm();
            Notification.show("Роль пользователя обновлена");
        }
    }

    private void clearForm() {
        currentUser = null;
        roleRadio.clear();
        userGrid.deselectAll();
    }
}
