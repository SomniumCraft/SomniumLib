package ru.somniumcraft.somniumlib.Database.jooq.GeneratorStrategies;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class ServerPrefixGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(final Definition definition, final Mode mode) {
        return "Server" + super.getJavaClassName(definition, mode);
    }
}
