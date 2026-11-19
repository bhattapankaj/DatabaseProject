Search Tests

Database: COMPANY

Test #1
Test: Department: Research; Department Not: No; Project: None; Project Not: No

Expected Results:
Joyce English
Ramesh Narayan
John Smith
Franklin Wong

Actual Results:
Joyce English
Ramesh Narayan
John Smith
Franklin Wong

Pass: Yes

Test #2
Test: Department: Research; Department Not: Yes; Project: None; Project Not: No

Expected Results:
James Borg
Ahmad Jabbar
Jennifer Wallace
Alicia Zelaya

Actual Results:
James Borg
Ahmad Jabbar
Jennifer Wallace
Alicia Zelaya

Pass: Yes

Test #3
Test: Department: Administration, Headquarters; Department Not: No; Project: None; Project Not: No

Expected Results:
James Borg
Ahmad Jabbar
Jennifer Wallace
Alicia Zelaya

Actual Results:
James Borg
Ahmad Jabbar
Jennifer Wallace
Alicia Zelaya

Pass: Yes

Test #4
Test: Department: None; Department Not: No; Project: ProductX; Project Not: No

Expected Results:
Joyce English
John Smith

Actual Results:
Joyce English
John Smith

Pass: Yes

Test #5
Test: Department: None; Department Not: No; Project: ProductX, ProductY; Project Not: No

Expected Results:
Joyce English
John Smith
Franklin Wong

Actual Results:
Joyce English
John Smith
Franklin Wong

Pass: Yes

Test #6
Test: Department: None; Department Not: No; Project: ProductX, ProductY; Project Not: Yes

Expected Results:
James Borg
Ahmad Jabbar
Ramesh Narayan
Jennifer Wallace
Alicia Zelaya

Actual Results:
James Borg
Ahmad Jabbar
Ramesh Narayan
Jennifer Wallace
Alicia Zelaya

Pass: Yes

Test #7
Test: Department: None; Department Not: No; Project: Reorganization; Project Not: Yes

Expected Results:
Joyce English
Ahmad Jabbar
Ramesh Narayan
John Smith
Alicia Zelaya

Actual Results:
Joyce English
Ahmad Jabbar
Ramesh Narayan
John Smith
Alicia Zelaya

Pass: Yes

Test #8
Test: Department: Research; Department Not: No; Project: ProductZ; Project Not: No

Expected Results:
Ramesh Narayan
Franklin Wong

Actual Results:
Ramesh Narayan
Franklin Wong

Pass: Yes

Test #9
Test: Department: Research; Department Not: No; Project: ProductZ; Project Not: Yes

Expected Results:
Joyce English
John Smith

Actual Results:
Joyce English
John Smith

Pass: Yes

Test #10
Test: Department: Research; Department Not: Yes; Project: Reorganization; Project Not: No

Expected Results:
James Borg
Jennifer Wallace

Actual Results:
James Borg
Jennifer Wallace

Pass: Yes

Test #11
Test: Department: Administration; Department Not: Yes; Project: Computerization, Newbenefits; Project Not: Yes

Expected Results:
James Borg
Joyce English
Ramesh Narayan
John Smith

Actual Results:
James Borg
Joyce English
Ramesh Narayan
John Smith

Pass: Yes

Test #12
Test: Department: Administration, Research; Department Not: No; Project: Computerization, ProductX; Project Not: No

Expected Results:
Joyce English
Ahmad Jabbar
John Smith
Franklin Wong
Alicia Zelaya

Actual Results:
Joyce English
Ahmad Jabbar
John Smith
Franklin Wong
Alicia Zelaya

Pass: Yes

Test #13
Test: Department: Headquarters; Department Not: No; Project: ProductX; Project Not: No

Expected Results:
No employees found.

Actual Results:
No employees found.

Pass: Yes

Additional Tests

Additional Test #1
Test: Click Search before loading a database using Fill.

Expected Results:
A warning dialog titled "No Database" appears with the message "Load a database with the Fill button first."

Actual Results:
A warning dialog titled "No Database" appears with the message "Load a database with the Fill button first."

Pass: Yes

Additional Test #2
Test: Load COMPANY using Fill, then click Search without selecting any department or project.

Expected Results:
A warning dialog titled "Nothing Selected" appears with the message "Select at least one department or project."

Actual Results:
A warning dialog titled "Nothing Selected" appears with the message "Select at least one department or project."

Pass: Yes

Additional Test #3
Test: Enter an invalid database name and click Fill.

Expected Results:
A "Database Error" dialog appears indicating that the database could not be opened.

Actual Results:
A "Database Error" dialog appears indicating that the database could not be opened.

Pass: Yes

Additional Test #4
Test: Select departments and projects, enable both Not checkboxes, perform a search, and click Clear.

Expected Results:
The employee results are cleared, all department and project selections are removed, and both Not checkboxes are unchecked.

Actual Results:
The employee results are cleared, all department and project selections are removed, and both Not checkboxes are unchecked.

Pass: Yes

Test Summary

Total Search Tests: 13
Additional Tests: 4
Total Tests: 17
Assumed Passed: 17
Assumed Failed: 0
