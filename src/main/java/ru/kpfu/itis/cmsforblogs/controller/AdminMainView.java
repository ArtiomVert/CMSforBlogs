package ru.kpfu.itis.cmsforblogs.controller;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("admin")
public class AdminMainView extends VerticalLayout {

    public AdminMainView() {
        add(new H1("Навигация в админе"));
        add(new RouterLink("Перейти в управление пользователями", UsersVaadin.class));
    }
}
