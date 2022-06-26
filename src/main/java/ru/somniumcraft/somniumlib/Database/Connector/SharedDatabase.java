package ru.somniumcraft.somniumlib.Database.Connector;

import lombok.Getter;

import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SharedDatabase {

    @Getter
    private List<String> sqlStatements = new LinkedList<>();

    //Default constructor
    public SharedDatabase() {

        sqlStatements.add(
                "CREATE TABLE IF NOT EXISTS `players` (`uuid` varchar(32) NOT NULL, `name` varchar(16) NOT NULL, `join_message` varchar(40) DEFAULT NULL,`leave_message` varchar(40) DEFAULT NULL, PRIMARY KEY (`uuid`));"
        );
    }
}
