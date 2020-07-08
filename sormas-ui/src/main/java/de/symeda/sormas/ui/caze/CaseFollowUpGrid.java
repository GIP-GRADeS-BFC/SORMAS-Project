package de.symeda.sormas.ui.caze;

import static de.symeda.sormas.ui.utils.FollowUpUtils.getVisitResultCssStyle;
import static de.symeda.sormas.ui.utils.FollowUpUtils.getVisitResultDescription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.DescriptionGenerator;
import com.vaadin.ui.Label;
import com.vaadin.ui.StyleGenerator;
import com.vaadin.ui.renderers.DateRenderer;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.caze.CaseCriteria;
import de.symeda.sormas.api.caze.CaseFollowUpDto;
import de.symeda.sormas.api.caze.CaseLogic;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.utils.DateHelper;
import de.symeda.sormas.api.utils.SortProperty;
import de.symeda.sormas.api.visit.VisitResult;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.utils.DateFormatHelper;
import de.symeda.sormas.ui.utils.FilteredGrid;
import de.symeda.sormas.ui.utils.UuidRenderer;

@SuppressWarnings("serial")
public class CaseFollowUpGrid extends FilteredGrid<CaseFollowUpDto, CaseCriteria> {

	private List<Date> dates = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public <V extends View> CaseFollowUpGrid(CaseCriteria criteria, Date referenceDate, int interval, Class<V> viewClass) {

		super(CaseFollowUpDto.class);
		setSizeFull();

		Date fromDate = DateHelper.subtractDays(referenceDate, interval - 1);
		criteria.followUpUntilFrom(DateHelper.getStartOfDay(fromDate));

		setColumns(
				CaseFollowUpDto.UUID,
				CaseFollowUpDto.PERSON,
				CaseFollowUpDto.REPORT_DATE,
				CaseFollowUpDto.FOLLOW_UP_UNTIL);

		setVisitColumns(referenceDate, interval, criteria);

		((Column<CaseFollowUpDto, String>) getColumn(CaseFollowUpDto.UUID)).setRenderer(new UuidRenderer());
		((Column<CaseFollowUpDto, Date>) getColumn(CaseFollowUpDto.REPORT_DATE))
			.setRenderer(new DateRenderer(DateFormatHelper.getDateFormat()));
		((Column<CaseFollowUpDto, Date>) getColumn(CaseFollowUpDto.FOLLOW_UP_UNTIL))
			.setRenderer(new DateRenderer(DateFormatHelper.getDateFormat()));

		for (Column<?, ?> column : getColumns()) {
			column.setCaption(I18nProperties.getPrefixCaption(CaseFollowUpDto.I18N_PREFIX, column.getId(), column.getCaption()));
		}

		addItemClickListener(e -> {
			if ((e.getColumn() != null && CaseFollowUpDto.UUID.equals(e.getColumn().getId())) || e.getMouseEventDetails().isDoubleClick()) {
				ControllerProvider.getCaseController().navigateToCase(e.getItem().getUuid());
			}
		});
	}

	public void setVisitColumns(Date referenceDate, int interval, CaseCriteria criteria) {

		setDataProvider(referenceDate, interval - 1);
		setCriteria(criteria);
		dates.forEach(date -> removeColumn(DateFormatHelper.formatDate(date)));

		setDates(referenceDate, interval);

		for (int i = 0; i < interval; i++) {
			String columnId = DateFormatHelper.formatDate(dates.get(i));
			addComponentColumn(followUpDto -> new Label("")).setId(columnId);

			final int index = i;
			getColumn(columnId).setCaption(columnId).setSortable(false).setStyleGenerator((StyleGenerator<CaseFollowUpDto>) item -> {
				final VisitResult visitResult = item.getVisitResults()[index];
				final Date date = dates.get(index);
				return getVisitResultCssStyle(
					visitResult,
					date,
					CaseLogic.getStartDate(item.getSymptomsOnsetDate(), item.getReportDate()),
					item.getFollowUpUntil());
			}).setDescriptionGenerator((DescriptionGenerator<CaseFollowUpDto>) item -> {
				final VisitResult visitResult = item.getVisitResults()[index];
				final Date date = dates.get(index);
				return getVisitResultDescription(
					visitResult,
					date,
					CaseLogic.getStartDate(item.getSymptomsOnsetDate(), item.getReportDate()),
					item.getFollowUpUntil());
			});
		}
	}

	public void reload() {
		getDataProvider().refreshAll();
	}

	public void setDataProvider(Date referenceDate, int interval) {

		DataProvider<CaseFollowUpDto, CaseCriteria> dataProvider = DataProvider.fromFilteringCallbacks(
			query -> FacadeProvider.getCaseFacade()
				.getCaseFollowUpList(
					query.getFilter().orElse(null),
					referenceDate,
					interval,
					query.getOffset(),
					query.getLimit(),
					query.getSortOrders()
						.stream()
						.map(sortOrder -> new SortProperty(sortOrder.getSorted(), sortOrder.getDirection() == SortDirection.ASCENDING))
						.collect(Collectors.toList()))
				.stream(),
			query -> (int) FacadeProvider.getCaseFacade().count(query.getFilter().orElse(null)));
		setDataProvider(dataProvider);
		setSelectionMode(SelectionMode.NONE);
	}

	private void setDates(Date referenceDate, int interval) {

		dates.clear();
		for (int i = 0; i < interval; i++) {
			dates.add(DateHelper.subtractDays(referenceDate, interval - i - 1));
		}
	}
}
