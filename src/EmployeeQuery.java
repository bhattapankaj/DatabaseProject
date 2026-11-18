
/**
 * Author: Taire
 * Description: Builds and runs the employee search query from the selected
 * departments and projects and the two Not checkboxes.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQuery {

    /**
     * Builds the SQL with one ? for every selected name.
     * Department part: E.Dno [NOT] IN (dept numbers of the selected names)
     * Project part:    [NOT] EXISTS (a WORKS_ON row for one of the selected projects)
     * If both are used they are joined with AND.
     */
    public static String buildQuery(int deptCount, boolean notDept,
            int projCount, boolean notProj) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT E.Fname, E.Lname FROM EMPLOYEE E WHERE 1 = 1");

        if (deptCount > 0) {
            sql.append(" AND E.Dno ");
            if (notDept) {
                sql.append("NOT ");
            }
            sql.append("IN (SELECT D.Dnumber FROM DEPARTMENT D WHERE D.Dname IN (");
            sql.append(placeholders(deptCount));
            sql.append("))");
        }

        if (projCount > 0) {
            sql.append(" AND ");
            if (notProj) {
                sql.append("NOT ");
            }
            sql.append("EXISTS (SELECT * FROM WORKS_ON W JOIN PROJECT P ON W.Pno = P.Pnumber");
            sql.append(" WHERE W.Essn = E.Ssn AND P.Pname IN (");
            sql.append(placeholders(projCount));
            sql.append("))");
        }

        sql.append(" ORDER BY E.Lname, E.Fname");
        return sql.toString();
    }

    // "?, ?, ?" for count = 3
    private static String placeholders(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        return sb.toString();
    }

    public static List<String> search(Connection conn, List<String> depts,
            boolean notDept, List<String> prjs, boolean notProj)
            throws SQLException {

        String sql = buildQuery(depts.size(), notDept, prjs.size(), notProj);
        List<String> names = new ArrayList<String>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int i = 1;

            for (String d : depts) {
                stmt.setString(i++, d);
            }

            for (String p : prjs) {
                stmt.setString(i++, p);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    names.add(rs.getString("Fname") + " " + rs.getString("Lname"));
                }
            }
        }

        return names;
    }
}
