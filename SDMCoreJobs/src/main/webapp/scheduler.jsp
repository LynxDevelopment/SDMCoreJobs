<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="Login">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Scheduler</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">
					Scheduler </span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			Current Status:
			<c:choose>
				<c:when test="${isStopped}">
					<span class="label label-important">Stopped</span>
				</c:when>
				<c:when test="${isInStandBy}">
					<span class="label label-warning">StandBy</span>
					<a href="schedplay.do"><i class="halflings-icon play"></i></a>
					<a href="schedstop.do"><i class="halflings-icon stop"></i></a>
				</c:when>
				<c:when test="${isStarted}">
					<span class="label label-success">Started</span>
					<a href="schedpause.do"><i class="halflings-icon pause"></i></a>
					<a href="schedstop.do"><i class="halflings-icon stop"></i></a>
				</c:when>
			</c:choose>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">
					Jobs </span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Job Name</th>
						<th>Job Group</th>
						<th>Cron Expression</th>
						<th>Next Fire Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jobs}" var="jobinfo">
						<tr>
							<c:forEach items="${jobinfo}" var="info">
								<td>${info}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
