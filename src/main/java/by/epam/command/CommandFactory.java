package by.epam.command;

import by.epam.command.admin.AddPublicationCommand;
import by.epam.command.admin.BanUserCommand;
import by.epam.command.admin.DeletePublicationCommand;
import by.epam.command.admin.SetPublicationNameCommand;
import by.epam.command.admin.SetPublicationPriceCommand;
import by.epam.command.admin.SetPublicationTypeCommand;
import by.epam.command.admin.SetUserBalanceCommand;
import by.epam.command.admin.SetUserNameCommand;
import by.epam.command.admin.UnbanUserCommand;
import by.epam.command.base.*;
import by.epam.command.base.ChangeLanguageCommand;
import by.epam.command.user.ChangeNameCommand;
import by.epam.command.user.ChangePasswordCommand;
import by.epam.command.user.SubscribeCommand;
import by.epam.command.user.UnsubscribeCommand;
import by.epam.constant.Pages;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

    private static Map<String, AbstractCommand> command;
    private static final Logger Logger = LogManager.getLogger(CommandFactory.class);

    /**
     * Need to choose put all possible request to map
     */
    public CommandFactory() {
        command = new HashMap<>();
        command.put("GET/login", new EmptyCommand());
        command.put("POST/login", new LoginCommand());
        command.put("GET/admin", new ForwardToProfileCommand(Pages.ADMIN_PATH, Pages.USER_PATH));
        command.put("GET/user", new ForwardToProfileCommand(Pages.ADMIN_PATH, Pages.USER_PATH));
        command.put("GET/profile", new ForwardToProfileCommand(Pages.ADMIN_PATH, Pages.USER_PATH));
        command.put("POST/ban", new BanUserCommand());
        command.put("POST/changelang", new ChangeLanguageCommand());
        command.put("POST/unban", new UnbanUserCommand());
        command.put("POST/add", new AddPublicationCommand());
        command.put("POST/delete", new DeletePublicationCommand());
        command.put("GET/main", new ForwardToMainCommand());
        command.put("GET/controller/error", new ForwardToErrorCommand());
        command.put("GET/logout", new LogoutCommand());
        command.put("POST/subscribe", new SubscribeCommand());
        command.put("POST/unsubscribe", new UnsubscribeCommand());
        command.put("POST/changename", new ChangeNameCommand());
        command.put("POST/changepassword", new ChangePasswordCommand());
        command.put("POST/adminchangename", new SetUserNameCommand());
        command.put("POST/changebalance", new SetUserBalanceCommand());
        command.put("POST/changepublicationprice", new SetPublicationPriceCommand());
        command.put("POST/changepublicationname", new SetPublicationNameCommand());
        command.put("POST/changepublicationtype", new SetPublicationTypeCommand());
        command.put("GET/admin/publications", new ForwardToProfileCommand(Pages.PUBLICATIONS_PATH, Pages.USER_PATH));
        command.put("GET/user/payments", new ForwardToProfileCommand(Pages.ADMIN_PATH, Pages.PAYMENTS_PATH));
        command.put("GET/admin/users", new ForwardToProfileCommand(Pages.USERS_PATH, Pages.USERS_PATH));
        command.put("POST/login/restore", new RestorePasswordCommand());

    }

    /**
     * Need to take method and path of request. By these parameters take command from the map of class
     * @param request need to take path and method
     * @return command
     */
    public AbstractCommand getCommand(HttpServletRequest request) {

        Logger.info("request key is " + request.getMethod() + request.getPathInfo());
        String method = request.getMethod();
        String pathInfo = request.getPathInfo();
        return command.get(method + pathInfo);
    }
}
