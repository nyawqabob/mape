package by.epam.dao;

import by.epam.entity.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityDAO<T extends Entity> extends AbstractDAO {

    /**
     * Need to take list of all entities from DB
     * @return list of all entities
     * @throws SQLException
     */
    protected List<T> takeAll() throws SQLException {
        List<T> list;
        String sql = getSelectAllQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        list = parseResultSetToList(rs);
        return list;
    }

    /**
     * Need to take entity by id
     * @param id to take entity
     * @return entity
     * @throws SQLException
     */
    protected T takeEntity(Object id) throws SQLException {
        T entity;
        String sql = getSelectEntityQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, id);
        ResultSet rs = statement.executeQuery();
        entity = parseResultSetToEntity(rs);
        return entity;
    }

    /**
     * Parsing result set to list of entities
     * @param rs parse it to list
     * @return list of entities
     */
    protected abstract List<T> parseResultSetToList(ResultSet rs);

    /**
     * Parsing result set to entity
     * @param rs parse it to entity
     * @return entity
     */
    protected abstract T parseResultSetToEntity(ResultSet rs);

    /**
     * Need to take select all query
     * @return string with select all query
     */
    protected abstract String getSelectAllQuery();

    /**
     * Need to take select entity query
     * @return string with select entity query
     */
    protected abstract String getSelectEntityQuery();
}
