# Search tests

Tested on the COMPANY database from `sql/company.sql`. Actual results matched the expected results for every test.

| # | Department | Not | Project | Not | Result | Pass |
|---|------------|-----|---------|-----|--------|------|
| 1 | Research | | | | Joyce English, Ramesh Narayan, John Smith, Franklin Wong | Yes |
| 2 | Research | X | | | James Borg, Ahmad Jabbar, Jennifer Wallace, Alicia Zelaya | Yes |
| 3 | Administration, Headquarters | | | | James Borg, Ahmad Jabbar, Jennifer Wallace, Alicia Zelaya | Yes |
| 4 | | | ProductX | | Joyce English, John Smith | Yes |
| 5 | | | ProductX, ProductY | | Joyce English, John Smith, Franklin Wong | Yes |
| 6 | | | ProductX, ProductY | X | James Borg, Ahmad Jabbar, Ramesh Narayan, Jennifer Wallace, Alicia Zelaya | Yes |
| 7 | | | Reorganization | X | Joyce English, Ahmad Jabbar, Ramesh Narayan, John Smith, Alicia Zelaya | Yes |
| 8 | Research | | ProductZ | | Ramesh Narayan, Franklin Wong | Yes |
| 9 | Research | | ProductZ | X | Joyce English, John Smith | Yes |
| 10 | Research | X | Reorganization | | James Borg, Jennifer Wallace | Yes |
| 11 | Administration | X | Computerization, Newbenefits | X | James Borg, Joyce English, Ramesh Narayan, John Smith | Yes |
| 12 | Administration, Research | | Computerization, ProductX | | Joyce English, Ahmad Jabbar, John Smith, Franklin Wong, Alicia Zelaya | Yes |
| 13 | Headquarters | | ProductX | | No employees found. | Yes |

## Other checks

| Test | Result | Pass |
|------|--------|------|
| Search before Fill | "No Database" warning: Load a database with the Fill button first. | Yes |
| Search with nothing selected | "Nothing Selected" warning: Select at least one department or project. | Yes |
| Fill with a wrong database name | "Database Error" pop-up saying the database could not be opened | Yes |
| Select items, check both Not boxes, Search, then Clear | Employee box emptied, selections removed, both Not boxes unchecked, lists still filled | Yes |

17 tests, 17 passed.
