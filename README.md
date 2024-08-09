<h1>DSMTASKTRACKER REST API</h1>

<p><strong>DSMTaskTracker</strong> is a RESTful API built using Java 17 and Spring Boot, designed to manage DSM tasks efficiently. It features secure JWT authentication, task management, activity logs, team collaboration, and more. The API is containerized using Docker for easy deployment.</p>

<h2>Table of Contents</h2>
<ul>
  <li><a href="#installation">Installation</a></li>
  <li><a href="#usage">Usage</a></li>
  <li><a href="#api-endpoints">API Endpoints</a></li>
  <li><a href="#testing">Testing</a></li>
  <li><a href="#deployment">Deployment</a></li>
  <li><a href="#live-demo">Live Demo</a></li>
  <li><a href="#contributing">Contributing</a></li>
</ul>

<h2 id="installation">Installation</h2>

<h3>Prerequisites</h3>
<ul>
  <li>Java 17</li>
  <li>Maven</li>
  <li>Docker</li>
</ul>

<h3>Setup</h3>
<ol>
  <li>Clone the repository:
    <pre><code>git clone https://github.com/yourusername/dsmtasktracker.git
cd dsmtasktracker</code></pre>
  </li>
  <li>Build the project using Maven:
    <pre><code>mvn clean install</code></pre>
  </li>
  <li>Configure your environment variables for email services:
    <ul>
      <li><code>EMAIL_SERVICE_USERNAME</code></li>
      <li><code>EMAIL_SERVICE_PASSWORD</code></li>
    </ul>
  </li>
  <li>Run the application locally:
    <pre><code>mvn spring-boot:run</code></pre>
  </li>
</ol>

<h2 id="usage">Usage</h2>

<p>You can use tools like Postman or curl to interact with the API. Below are some of the main API endpoints.</p>

<h2 id="api-endpoints">API Endpoints</h2>

<h3>Authentication</h3>
<ul>
  <li><strong>Login:</strong>
    <pre><code>POST /auth/login</code></pre>
    Request Body: <code>LoginRequest</code>
  </li>
  <li><strong>Register:</strong>
    <pre><code>POST /auth/register</code></pre>
    Request Body: <code>User</code>
  </li>
  <li><strong>Verify Email:</strong>
    <pre><code>GET /auth/verifyemail?email={email}</code></pre>
  </li>
  <li><strong>Generate OTP:</strong>
    <pre><code>POST /auth/generateOTP?email={email}</code></pre>
  </li>
  <li><strong>Validate OTP:</strong>
    <pre><code>POST /auth/validateOTP?email={email}&amp;OTP={OTP}</code></pre>
  </li>
  <li><strong>Reset Password:</strong>
    <pre><code>POST /auth/resetPassword</code></pre>
    Request Body: <code>ResetPasswordBody</code>
  </li>
</ul>

<h3>Activities</h3>
<ul>
  <li><strong>Get Activities by User ID:</strong>
    <pre><code>GET /api/activities/{userId}</code></pre>
  </li>
  <li><strong>Delete Activities by User ID:</strong>
    <pre><code>DELETE /api/activities/{userId}</code></pre>
  </li>
</ul>

<h3>Tasks</h3>
<ul>
  <li><strong>Add New Task:</strong>
    <pre><code>POST /api/tasks</code></pre>
    Request Body: <code>Task</code>
  </li>
  <li><strong>Get All Tasks by User ID:</strong>
    <pre><code>GET /api/tasks/{userId}</code></pre>
  </li>
  <li><strong>Get Tasks by User ID and Date:</strong>
    <pre><code>GET /api/tasks/date/{userId}?date={date}</code></pre>
  </li>
  <li><strong>Update Task by Task ID:</strong>
    <pre><code>PUT /api/tasks/{taskId}</code></pre>
    Request Body: <code>Task</code>
  </li>
  <li><strong>Delete Task by Task ID:</strong>
    <pre><code>DELETE /api/tasks/{taskId}</code></pre>
  </li>
</ul>

<h3>Teams</h3>
<ul>
  <li><strong>Add New Team:</strong>
    <pre><code>POST /api/teams</code></pre>
    Request Body: <code>Team</code>
  </li>
  <li><strong>Get All Teams by User ID:</strong>
    <pre><code>GET /api/teams/{userId}</code></pre>
  </li>
  <li><strong>Update Team by Team ID:</strong>
    <pre><code>PUT /api/teams/{teamId}</code></pre>
    Request Body: <code>Team</code>
  </li>
  <li><strong>Delete Team by Team ID:</strong>
    <pre><code>DELETE /api/teams/{teamId}</code></pre>
  </li>
  <li><strong>Delete Multiple Teams:</strong>
    <pre><code>DELETE /api/teams</code></pre>
    Request Body: <code>List&lt;String&gt;</code>
  </li>
</ul>

<h3>Users</h3>
<ul>
  <li><strong>Get User by ID:</strong>
    <pre><code>GET /api/users/{userId}</code></pre>
  </li>
  <li><strong>Get User by Email:</strong>
    <pre><code>GET /api/users/email/{email}</code></pre>
  </li>
  <li><strong>Delete User by ID:</strong>
    <pre><code>DELETE /api/users/{userId}</code></pre>
  </li>
  <li><strong>Update User by ID:</strong>
    <pre><code>PUT /api/users/{userId}</code></pre>
    Request Body: <code>User</code>
  </li>
  <li><strong>Update User by Email:</strong>
    <pre><code>PUT /api/users/email/{email}</code></pre>
    Request Body: <code>User</code>
  </li>
</ul>

<h2 id="testing">Testing</h2>

<p>To run the unit tests, use the following command:</p>
<pre><code>mvn test</code></pre>

<h2 id="deployment">Deployment</h2>

<p>To deploy the API using Docker:</p>
<ol>
  <li>Build the Docker image:
    <pre><code>docker build -t dsmtasktracker .</code></pre>
  </li>
  <li>Run the Docker container:
    <pre><code>docker run -p 8080:8080 dsmtasktracker</code></pre>
  </li>
</ol>

<p>The application is now accessible at <code>http://localhost:8080</code>.</p>

<h2 id="live-demo">Live Demo</h2>

<p>The live version of the API is hosted <a href="https://dsmtasktracker.onrender.com">here</a>.</p>

<p><strong>Note:</strong> Since the API is deployed on a shared server, the initial response may take a few minutes, but subsequent requests should be faster.</p>

<h2 id="contributing">Contributing</h2>

<p>Contributions are welcome! Please fork this repository and submit a pull request for any improvements or new features.</p>
