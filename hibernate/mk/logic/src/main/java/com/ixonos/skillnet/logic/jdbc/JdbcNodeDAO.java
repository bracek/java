package com.ixonos.skillnet.logic.jdbc;

import com.ixonos.skillnet.logic.bean.Node;

public interface JdbcNodeDAO extends JdbcGenericDAO<Node> {

	int getChildCount(Node node);

}
