
<!-- name1 -->
<tr>
  <th>Forename</th>
  <td>
    <html:text property="name1" />
  </td>
</tr>

<!-- name2 -->
<tr>
  <th>Surname</th>
  <td><html:text property="name2" /></td>
</tr>

<!-- sex -->
<tr>
  <th>Sex</th>
  <td>
    <html:select property="sex">
      <html:option value="">---select---</html:option>
      <html:option value="M">Male</html:option>
      <html:option value="F">Female</html:option>
    </html:select>
  </td>
</tr>

<!-- subject type -->
<tr>
  <th>Subject Type</th>
  <td>
    <html:select property="subjectTypeId">
      <html:option value="">---select---</html:option>
      <html:option value="1">Family Member</html:option>
      <html:option value="3">Friend</html:option>
      <html:option value="2">Pet</html:option>
    </html:select>
  </td>
</tr>

<!-- notes -->
<tr>
  <th>Notes</th>
  <td><html:textarea property="notes" cols="60" rows="10"/></td>
</tr>

